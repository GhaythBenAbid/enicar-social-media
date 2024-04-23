import 'dart:convert';

import 'package:enicar_social_media/Models/Club.dart';
import 'package:enicar_social_media/Utils/Constants.dart';
import 'package:http/http.dart';

class ClubService {
  final String url = Constants.API_URL + "/api/club";
  final String token = "Bearer ${Constants.API_TOKEN}";

  Future<List<Club>> getClubs() async {
    Response response = await get(Uri.parse(url), headers: {
      "Authorization": token,
    });
    if (response.statusCode == 200) {
      List<dynamic> body = jsonDecode(response.body);
      List<Club> Clubs =
          body.map((dynamic item) => Club.fromJson(item)).toList();
      return Clubs;
    } else {
      throw "Can't get Clubs.";
    }
  }
}
