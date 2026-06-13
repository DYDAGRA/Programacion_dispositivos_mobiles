import 'package:flutter/material.dart';

class PantallaInicio extends StatelessWidget {
  const PantallaInicio({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bienvenido'),
        centerTitle: true,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Icon(Icons.home, size: 100, color: Colors.blue),
              const SizedBox(height: 30),
              const Text(
                '¡Hola! Bienvenido a mi App Personal',
                style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 20),
              const Text(
                'Descubre quién soy',
                style: TextStyle(fontSize: 18),
              ),
              const SizedBox(height: 50),
              ElevatedButton(
                onPressed: () {}, 
                child: const Text('Ver mi perfil'),
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}