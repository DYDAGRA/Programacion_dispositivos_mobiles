import 'package:flutter/material.dart';

class PantallaHobbies extends StatelessWidget {
  const PantallaHobbies({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Mis Hobbies')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Mis Hobbies',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 20),

            _buildHobbyCard(
              icon: Icons.fitness_center,
              title: 'Entrenar',
              description: 'Hago ejercicio y salgo a correr',
            ),
            _buildHobbyCard(
              icon: Icons.menu_book,
              title: 'Leer',
              description: 'Me gusta leer novelas ligeras de animes',
            ),
            _buildHobbyCard(
              icon: Icons.videogame_asset,
              title: 'Videojuegos',
              description: 'Me gusta jugar videojuegos',
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildHobbyCard({
    required IconData icon,
    required String title,
    required String description,
  }) {
    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      child: ListTile(
        leading: Icon(icon, size: 40, color: Colors.deepPurple),
        title: Text(title, style: const TextStyle(fontWeight: FontWeight.bold)),
        subtitle: Text(description),
      ),
    );
  }
}