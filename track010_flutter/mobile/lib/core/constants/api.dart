// lib/core/constats/api.dart

class ApiConstants {
  static const String baseUrl = "http://localhost:8080"; //개발환경

  static const String auth = "$baseUrl/auth";
  static const String posts = "$baseUrl/api/posts";
  static const String login = "$auth/login";
  static const String signup = "$auth/signup";
  static const String refresh = "$auth/refresh";
  static const String logout = "$auth/logout";
  static const String me = "$auth/me";
}