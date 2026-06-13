import 'package:flutter/material.dart';

class PantallaPerfil extends StatelessWidget {
  const PantallaPerfil({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Mi Perfil')),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            // Imagen de perfil
            Center(
              child: ClipOval(
                child: Image.network(
                  'https://scontent.faqp1-1.fna.fbcdn.net/v/t39.30808-1/364795106_3418224188441747_5910431085447633790_n.jpg?stp=c0.0.1340.1340a_dst-jpg_tt6&cstp=mx1340x1340&ctp=s200x200&_nc_cat=104&ccb=1-7&_nc_sid=e99d92&_nc_eui2=AeHSDhaSdyvq7y5CF89SfH78N81bFmxzNqw3zVsWbHM2rLW39fEhOIcJdBPc5B0pjX9UOqJLuAyH1qQrVxoXfvPd&_nc_ohc=1wfgp2EzFmUQ7kNvwE2hkdf&_nc_oc=AdppGFkwxtryVcNYsbky64LbrhwySQOhz3EFOafQ_fNpJdBEy-LgDcHsWYDP1w32o7jWaecavGyJ5lXmRxn2UWyK&_nc_zt=24&_nc_ht=scontent.faqp1-1.fna&_nc_gid=6tMQn97m1a9HLUrckkPG3w&_nc_ss=7b2a8&oh=00_Af8F2wpG9uqYgIFb92HYVjIBKDfVRIsT-QKFaTkLuvA5sg&oe=6A329FD5', // Cambia esta URL por tu foto
                  width: 180,
                  height: 180,
                  fit: BoxFit.cover,
                ),
              ),
            ),
            const SizedBox(height: 20),

            const Text(
              'Dylan Dávila Grau',
              style: TextStyle(fontSize: 26, fontWeight: FontWeight.bold),
            ),
            const Text(
              'Estudiante de Desarrollo de Software',
              style: TextStyle(fontSize: 18, color: Colors.grey),
            ),
            const SizedBox(height: 30),

            const Divider(),
            const SizedBox(height: 20),

            const Row(
              children: [
                Icon(Icons.email, color: Colors.blue),
                SizedBox(width: 10),
                Text('ddavilag@ulasalle.edu.pe'),
              ],
            ),
            const SizedBox(height: 15),
            const Row(
              children: [
                Icon(Icons.phone, color: Colors.blue),
                SizedBox(width: 10),
                Text('+51 123 456 789'),
              ],
            ),
            const SizedBox(height: 15),
            const Row(
              children: [
                Icon(Icons.location_on, color: Colors.blue),
                SizedBox(width: 10),
                Text('Arequipa, Perú'),
              ],
            ),
          ],
        ),
      ),
    );
  }
}