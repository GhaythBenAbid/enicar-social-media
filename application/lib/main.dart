import 'package:enicar_social_media/Screens/Clubs.dart';
import 'package:enicar_social_media/Screens/Feed.dart';
import 'package:enicar_social_media/Widgets/NavBar.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

void main() {
  CustomImageCache();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int _currentIndex = 0;

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
        body: IndexedStack(
          index: _currentIndex,
          children: [
            Feed(),
            Clubs(),
          ],
        ),
        bottomNavigationBar: BottomNavigationBar(
          onTap: (int index) {
            setState(() {
              _currentIndex = index;
            });
          },
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.house), // Replace 'home' with 'house'
              label: 'Feed',
            ),
            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.peopleGroup),
              label: 'Clubs',
            ),
          ],
          currentIndex: _currentIndex,
          showUnselectedLabels: true,
          showSelectedLabels: true,
        ),
      ),
    );
  }
}

class CustomImageCache extends WidgetsFlutterBinding {
  @override
  ImageCache createImageCache() {
    ImageCache imageCache = super.createImageCache();
    // Set your image cache size
    imageCache.maximumSizeBytes = 1024 * 1024 * 100; // 100 MB
    return imageCache;
  }
}
