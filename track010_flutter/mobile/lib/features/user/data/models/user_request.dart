// lib/features/user/data/models/user_request.dart

class UserRequest {
  final String email;
  final String password;
  final String nickname;
  final String provider;

  UserRequest({
    required this.email,
    required this.password,
    required this.nickname,
    this.provider = "local",
  });

  Map<String, dynamic> toJson() {
    return {
      "email": email,
      "password": password,
      "nickname": nickname,
      "provider": provider,
    };
  }
}