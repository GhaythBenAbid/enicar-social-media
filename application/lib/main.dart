import 'dart:convert';

import 'package:enicar_social_media/Models/Post.dart';
import 'package:enicar_social_media/Screens/Feed.dart';
import 'package:enicar_social_media/Widgets/NavBar.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      // title: 'Flutter Demo',
      theme: ThemeData(
        textTheme: GoogleFonts.montserratTextTheme(),
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue[900]!),
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: NavBar(),
        body: Feed(),
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title, required this.data});

  final String title;
  final List<Post> data;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: NavBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            const SizedBox(height: 20),
            Text(
              widget.data[0].content,
              style: TextStyle(fontSize: 18),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}

//array of posts
// Future<List<Post>> fetchData() async {
//   final response = await http.get(
//     Uri.parse('https://h6c3v8rt-9000.euw.devtunnels.ms/api/post'),
//     headers: {
//       'Authorization':
//            
//     },
//   );
//   if (response.statusCode == 200) {
//     List jsonResponse = json.decode(response.body);
//     try {
//       List<Post> posts =
//           jsonResponse.map((data) => Post.fromJson(data)).toList();
//     } catch (e) {
//       print(e);
//     }
//     // List<Post> posts = jsonResponse.map((data) => Post.fromJson(data)).toList();

//     return jsonResponse.map((data) => Post.fromJson(data)).toList();
//   } else {
//     throw Exception('Failed to fetch data');
//   }
// }
