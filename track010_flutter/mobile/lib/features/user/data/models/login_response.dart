// lib/features/user/data/models/login_response.dart
import 'user_response.dart';

class LoginResponse {
  final String accessToken;
  final String refreshToken;
  final UserResponse user;

  LoginResponse({
    required this.accessToken,
    required this.refreshToken,
    required this.user,
  });



  factory LoginResponse.fromJson(Map<String, dynamic> json) {
    return LoginResponse(
      accessToken: json['accessToken'],
      refreshToken: json['refreshToken'] ?? "", // refreshToken이 없을 수도 있음
      user: UserResponse.fromJson(json['user']),
    );
  }
}

