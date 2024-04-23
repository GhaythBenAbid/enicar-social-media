import 'dart:convert';

import 'package:enicar_social_media/Models/Post.dart';
import 'package:enicar_social_media/Utils/Constants.dart';
import 'package:http/http.dart';

class PostService {
  final String url = Constants.API_URL + "/api/post";
  final String token = "Bearer ${Constants.API_TOKEN}";

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
