<h1 align="center">🌤️ WeatherAppRealTime</h1>

<p align="center">
 Aplikasi cuaca real-time berbasis Jetpack Compose. 
  Menampilkan cuaca saat ini berdasarkan lokasi pengguna, pencarian 
  kota di seluruh dunia, prakiraan cuaca mingguan interaktif, kualitas udara, dan dukungan multi-bahasa. Aplikasi juga mendukung dark mode, landscape mode, serta ikon dan background cuaca yang dinamis.
</p>

<br/>
<p align="center">
<img src="https://github.com/user-attachments/assets/99b5a74f-ba7c-42c3-b32d-1ea11582254d" width="200"> | <img src="https://github.com/user-attachments/assets/44024d44-0b0c-469f-b153-82afc24bbc32" width="200"/>
</p>

<!-- BADGES -->
<p align="center">

  <img src="https://img.shields.io/badge/Jetpack%20Compose-1.7-blue"/>
  <img src="https://img.shields.io/badge/Kotlin-1.9-purple"/>
  <img src="https://img.shields.io/badge/Material%20Design%203-✓-green"/>
  <img src="https://img.shields.io/badge/Platform-Android-black"/>
</p>

---

## ✨ Fitur Utama

### 1️⃣ Real-Time Weather (Berdasarkan Lokasi)
- Pelacakan otomatis menggunakan GPS  
- Menampilkan cuaca aktual sesuai lokasi  
- Data selalu update ketika lokasi berubah  

### 2️⃣ Pencarian Kota (Global Search)
- Cari kota mana saja di seluruh dunia  
- Cuaca langsung berubah sesuai kota  

### 3️⃣ Weather Card Utama (Dynamic)
- Ikon cuaca dinamis (cerah, hujan, mendung, dll.)  
- Background gradien mengikuti kondisi cuaca  
- Menampilkan suhu, feels like, deskripsi, tanggal  

### 4️⃣ Air Quality Card (Update Otomatis)
Menampilkan:
- Feels Like  
- Angin  
- Kelembapan  
- Awan  
- SO₂  
- O₃  
(Otomatis update saat hari atau kota berubah)

### 5️⃣ Prakiraan Mingguan (Weekly Forecast)
- Menampilkan 7 hari cuaca  
- Bisa klik hari tertentu  
- Weather card & air quality ikut berubah  

### 6️⃣ Dark Mode & Light Mode
- Mengikuti tema sistem  
- UI Material You  

### 7️⃣ Multi Bahasa (ID & EN)
- Mengikuti bahasa HP  
- Full translation  

### 8️⃣ Landscape Mode Support
- Tetap rapi ketika layar diputar  

---

## 🧩 Tech Stack

### **Frontend**
- Kotlin  
- Jetpack Compose  
- Material Design 3  
- Navigation Compose  
- Coil (ikon cuaca)

### **Architecture**
- MVVM  
- ViewModel + StateFlow  
- Repository Pattern  

### **Networking**
- Retrofit  
- OkHttp  
- OpenWeather / OpenMeteo API  

### **Localization**
- values/strings.xml  
- values-en/strings.xml  

### **Theme**
- Material Theme (light/dark)  
- Custom colors, typography, shapes  

---

<h2>📁 Struktur Folder</h2>

<pre>
app/
└── src/
    └── main/
        ├── java/com/example/weatherapprealtime/
        │    ├── api/
        │    ├── repository/
        │    ├── viewmodel/
        │    ├── utils/
        │    └── ui/
        │         ├── components/
        │         ├── model/
        │         ├── screens/
        │         ├── theme/
        │         └── WeatherScreen.kt
        ├── res/
        │    ├── drawable/
        │    ├── mipmap/
        │    ├── values/
        │    └── values-en/
        └── AndroidManifest.xml
</pre>

---
<h2>📦 Instalasi & Menjalankan</h2>
1️⃣ Clone Repository
git clone https://github.com/ClarestaDaifyW/WeatherApp.git
cd WeatherAppRealTime

2️⃣ Buka di Android Studio

Klik Open > Existing Project

Pilih folder project kamu

3️⃣ Tambahkan API Key

Buat file:

app/src/main/res/values/secrets.xml


Isi:

<resources>
    <string name="api_key">YOUR_API_KEY</string>
</resources>


API Key dari:
https://openweathermap.org/api

4️⃣ Jalankan Aplikasi

Hubungkan HP / emulator

Klik Run ▶

Izinkan akses lokasi

5️⃣ (Opsional) Build APK
Build > Build APK(s)

<h2>📸 Preview UI</h2>

| Home (Light) | Home (Dark) |
|--------------|-------------|
| <img src="https://github.com/user-attachments/assets/99b5a74f-ba7c-42c3-b32d-1ea11582254d" width="250"> | <img src="https://github.com/user-attachments/assets/44024d44-0b0c-469f-b153-82afc24bbc32" width="250"> |



<h2>📝 Lisensi</h2>

MIT License — bebas digunakan & dikembangkan.

