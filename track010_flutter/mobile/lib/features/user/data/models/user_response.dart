// lib/features/user/data/models/user_response.dart
class UserResponse {
  final int id;
  final String email;
  final String nickname;
  final String provider;
  final String role;

  UserResponse({
    required this.id,
    required this.email,
    required this.nickname,
    required this.provider,
    required this.role,
  });

  factory UserResponse.fromJson(Map<String, dynamic> json) {
    return UserResponse(
      id: json['id'],
      email: json['email'],
      nickname: json['nickname'],
      provider: json['provider'],
      role: json['role'],
    );
  }
}
