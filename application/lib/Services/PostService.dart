import 'dart:convert';

import 'package:enicar_social_media/Models/Post.dart';
import 'package:http/http.dart';

class PostService {
  final String url = "https://h6c3v8rt-9000.euw.devtunnels.ms/api/post";
  final String token =
      "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcm9qZXRAZW5pY2FyLnVjYXIudG4iLCJleHAiOjE3MTQ0MTUzMzl9.LxLF9pCs7KKzrWAkp270JnFui5IjCxfT1YqE9Vidsg0ZF3YUG8yxkVB7LpB9XFd-4TDMPE8vDMiHqTfyVAowgw";

  Future<List<Post>> getPosts() async {
    Response response = await get(Uri.parse(url), headers: {
      "Authorization": token,
    });
    if (response.statusCode == 200) {
      List<dynamic> body = jsonDecode(response.body);
      List<Post> posts =
          body.map((dynamic item) => Post.fromJson(item)).toList();
      return posts;
    } else {
      throw "Can't get posts.";
    }
  }
}
