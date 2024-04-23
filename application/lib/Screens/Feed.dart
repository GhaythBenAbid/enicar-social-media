import 'package:enicar_social_media/Models/Post.dart';
import 'package:enicar_social_media/Services/PostService.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:intl/intl.dart';

class Feed extends StatefulWidget {
  @override
  State<Feed> createState() => _FeedState();
}

class _FeedState extends State<Feed> {
  PostService postService = PostService();
  late Future<List<Post>> posts;

  initState() {
    super.initState();
    posts = postService.getPosts();
    //for loop to get the posts
    posts.then((List<Post> postList) {
      for (var post in postList) {
        print(post.content);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: posts,
      builder: (BuildContext context, AsyncSnapshot<List<Post>> snapshot) {
        if (snapshot.hasData) {
          return ListView.builder(
            itemCount: snapshot.data!.length,
            itemBuilder: (BuildContext context, int index) {
              return postCard(snapshot.data![index]);
            },
          );
        } else {
          return Center(
            child: CircularProgressIndicator(),
          );
        }
      },
    );
  }

  Container postCard(Post post) {
    return Container(
      padding: EdgeInsets.all(15.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            children: <Widget>[
              CircleAvatar(
                backgroundImage:
                    // if image is null, use a placeholder
                    post.author!.profilePicture != null
                        ? NetworkImage(post.author!.profilePicture!)
                        : NetworkImage(
                            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"),
                radius: 20.0,
              ),
              SizedBox(width: 7.0),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(post.author!.firstName! + " " + post.author!.lastName!,
                      style: TextStyle(
                          fontWeight: FontWeight.bold, fontSize: 17.0)),
                  SizedBox(height: 5.0),
                  Text(
                      DateFormat('yyyy-MM-dd – kk:mm')
                          .format(post.date)
                          .toString(),
                      style: TextStyle(fontSize: 12.0, color: Colors.grey)),
                ],
              ),
            ],
          ),
          SizedBox(height: 20.0),
          Text(post.content, style: TextStyle(fontSize: 15.0)),
          SizedBox(height: 10.0),
          post.image != null
              ? Image.network(
                  post.image!,
                  width: double.infinity,
                )
              : SizedBox(height: 0.0, width: 0.0),
          SizedBox(height: 10.0),
          Divider(height: 30.0),
        ],
      ),
    );
  }
}
