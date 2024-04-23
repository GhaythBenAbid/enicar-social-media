import 'package:enicar_social_media/Models/Club.dart';
import 'package:enicar_social_media/Services/ClubService.dart';
import 'package:flutter/material.dart';

class Clubs extends StatefulWidget {
  const Clubs({super.key});

  @override
  State<Clubs> createState() => _ClubsState();
}

class _ClubsState extends State<Clubs> {
  ClubService clubService = ClubService();
  late Future<List<Club>> clubs;

  initState() {
    super.initState();
    clubs = clubService.getClubs();
  }

  @override
  Widget build(BuildContext context) {
    //show the clubs in cards
    return FutureBuilder(
      future: clubs,
      builder: (BuildContext context, AsyncSnapshot<List<Club>> snapshot) {
        if (snapshot.hasData) {
          return ListView.builder(
            itemCount: snapshot.data!.length,
            itemBuilder: (BuildContext context, int index) {
              return Card(
                child: Container(
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: NetworkImage(snapshot.data![index].banner!),
                      fit: BoxFit.cover,
                    ),
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Container(
                        color:
                            Colors.black.withOpacity(0.5), // Add black overlay
                        padding: EdgeInsets.symmetric(
                            vertical: 50.0, horizontal: 15.0),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            CircleAvatar(
                              backgroundImage: NetworkImage(
                                snapshot.data![index].logo!,
                              ),
                              radius: 30.0, // Set the radius of the circle
                            ),
                            SizedBox(height: 10.0),
                            Text(
                              snapshot.data![index].name!,
                              style: TextStyle(
                                fontSize: 20.0,
                                fontWeight: FontWeight.bold,
                                color: Colors.white, // Set text color to white
                              ),
                            ),
                            SizedBox(height: 10.0),
                            Text(
                              snapshot.data![index].description!,
                              style: TextStyle(
                                fontSize: 10.0,
                                color: Colors.white, // Set text color to white
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              );
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
}
