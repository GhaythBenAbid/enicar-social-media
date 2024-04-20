import 'package:enicar_social_media/Models/Field.dart';

class User {
  int id;
  String? firstName;
  String? lastName;
  String? email;
  String? password;
  String? role;
  String? birthDate;
  bool? verified;
  String? profilePicture;
  String? coverPicture;
  String? aboutMe;
  Field field;

  User({
    required this.id,
    required this.firstName,
    required this.lastName,
    required this.email,
    required this.password,
    required this.role,
    required this.birthDate,
    required this.verified,
    required this.profilePicture,
    required this.coverPicture,
    required this.aboutMe,
    required this.field,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      email: json['email'],
      password: json['password'],
      role: json['role'],
      birthDate: json['birthDate'],
      verified: json['verified'],
      profilePicture: json['profilePicture'],
      coverPicture: json['coverPicture'],
      aboutMe: json['aboutMe'],
      field: Field.fromJson(json['field']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'firstName': firstName,
      'lastName': lastName,
      'email': email,
      'password': password,
      'role': role,
      'birthDate': birthDate,
      'verified': verified,
      'profilePicture': profilePicture,
      'coverPicture': coverPicture,
      'aboutMe': aboutMe,
      'field': field.toJson(),
    };
  }
}
