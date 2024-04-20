import 'package:enicar_social_media/Models/User.dart';

class Post {
  int id;
  String content;
  String? image;
  User? author;
  DateTime date;
  List<String> tags;
  bool visibility;

  Post({
    required this.id,
    required this.content,
    required this.image,
    required this.author,
    required this.date,
    required this.tags,
    required this.visibility,
  });

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      id: json['id'],
      content: json['content'],
      image: json['image'],
      author: User.fromJson(json['author']),
      date: DateTime.parse(json['date']),
      tags: List<String>.from(json['tags']),
      visibility: json['visibility'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'content': content,
      'image': image,
      'author': author!.toJson(),
      'date': date.toIso8601String(),
      'tags': tags,
      'visibility': visibility,
    };
  }
}
