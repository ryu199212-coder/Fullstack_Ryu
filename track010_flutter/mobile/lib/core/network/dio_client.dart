// lib/core/network/dio_client.dart
import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:mobile/core/constants/api.dart';
import 'package:mobile/core/utils/token_manager.dart';

class DioClient {
  final Dio dio;
  final TokenManager tokenManager;

  DioClient({required this.tokenManager})
      : dio = Dio(BaseOptions(
          baseUrl: ApiConstants.baseUrl,
          // ✅ 브라우저에서 쿠키 전송 허용
          extra: {"withCredentials": true},
        )) {
    dio.interceptors.add(InterceptorsWrapper(
      onRequest: (options, handler) async {
        // ✅ Access Token 자동 추가
        final accessToken = await tokenManager.getAccessToken();
        if (accessToken != null) {
          options.headers["Authorization"] = "Bearer $accessToken";
        }
        return handler.next(options);
      },
      onError: (DioError error, handler) async {
        // ✅ Access Token 만료 시 Refresh 처리
        if (error.response?.statusCode == 401) {
          final refreshToken = await tokenManager.getRefreshToken();
          if (refreshToken != null) {
            try {
              final response = await dio.post(
                ApiConstants.refresh,
                data: jsonEncode({"refreshToken": refreshToken}),
                options: Options(headers: {"Content-Type": "application/json"}),
              );

              if (response.statusCode == 200) {
                final newAccess = response.data["accessToken"];
                final newRefresh = response.data["refreshToken"];

                await tokenManager.saveTokens(newAccess, newRefresh);

                // 원래 요청 다시 시도
                final retryRequest = error.requestOptions;
                retryRequest.headers["Authorization"] = "Bearer $newAccess";
                final retryResponse = await dio.fetch(retryRequest);
                return handler.resolve(retryResponse);
              }
            } catch (e) {
              await tokenManager.clearTokens();
            }
          }
        }
        return handler.next(error);
      },
    ));
  }
}
