// lib/features/user/domain/entities/user.dart

class User {
  final String email;
  final String nickname;
  final String provider;
  final String role;

  User({
    required this.email,
    required this.nickname,
    this.provider = "local",
    this.role = "ROLE_USER",
  });
}