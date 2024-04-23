import 'package:enicar_social_media/Models/User.dart';

class Club {
  int? id;
  String? name;
  String? logo;
  String? banner;
  String? description;
  int? creationYear;
  User? responsible;
  String? content;

  Club(
      {this.id,
      this.name,
      this.logo,
      this.banner,
      this.description,
      this.creationYear,
      this.responsible,
      this.content});

  Club.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    logo = json['logo'];
    banner = json['banner'];
    description = json['description'];
    creationYear = json['creationYear'];
    responsible =
        json['responsible'] != null ? User.fromJson(json['responsible']) : null;
    content = json['content'];
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'logo': logo,
      'banner': banner,
      'description': description,
      'creationYear': creationYear,
      'responsible': responsible!.toJson(),
      'content': content,
    };
  }
}
