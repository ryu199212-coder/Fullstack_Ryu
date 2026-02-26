// lib/core/error/app_exception.dart
class AppException implements Exception {
  final String message;
  final int? statusCode;

  AppException(this.message, [this.statusCode]);

  @override
  String toString() => "AppException: $message (code: $statusCode)";
}