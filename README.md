# 🎨 Donot_match_the_name_match_color 🧠

Welcome to **Donot_match_the_name_match_color**, a fast-paced, brain-teasing JavaScript game inspired by the famous **Stroop Effect**!

The challenge sounds simple, but your brain might beg to differ: **Ignore the word written on the screen, and focus purely on the color it is painted in!**

This project was built using pure frontend technologies to sharpen logic, master DOM manipulation, and practice dynamic event handling in a fun, interactive way.

---

## 🕹️ How to Play

1. 🎯 **Look at the text** displayed on the screen.
2. 🛑 **Do not read the word!** (e.g., If the word says "RED" but is colored **Blue**, your brain will want to say Red).
3. 🔵 **Click the button** that matches the actual font color of the text.
4. ⏱️ **Race against the clock** to get the highest score before time runs out!

---

## 🚀 Features

* 🧠 **Brain-Training Logic:** Implements the Stroop Effect to challenge cognitive processing speed.
* ⚡ **Dynamic Color Generation:** Randomly pairs color names with different text colors so no two rounds are the same.
* ⏱️ **Score & Timer System:** Keeps track of your current streak and pushes you to play faster.
* 🎨 **Clean, Vibrant UI:** Minimalist and modern design focused entirely on gameplay.
* 📱 **Responsive Design:** Playable on desktops, tablets, and mobile devices.

---

## 🛠️ Tech Stack

This project is built using standard web technologies with no external frameworks or dependencies:

* **HTML5** – For the semantic layout and game structure.
* **CSS3** – For the vibrant styling, button layouts, and smooth animations.
* **JavaScript (ES6+)** – For the core game logic, timers, randomized state management, and DOM updates.

---

## 📂 Project Structure

```text
Donot_match_the_name_match_color/
├── index.html      # The game layout and structure
├── style.css       # The styling, colors, and responsive layout
└── script.js       # The brain/logic behind the game

```

---

## 💻 Code Highlights & Concepts Learned

Building this game provided hands-on experience with several core web development concepts:

### 🧠 1. The Stroop Logic

Ensuring the game generates confusing combinations (like the word "GREEN" rendered in yellow text) required managing two independent data states simultaneously: the **text value** and the **color value**.

### ⚡ 2. DOM Manipulation & Event Handling

We utilized JavaScript to listen for clicks on color buttons, instantly validate the player's choice against the current color state, and update the score and text seamlessly without reloading the page.

### ⏱️ 3. Asynchronous JavaScript

Implemented `setInterval` and `clearInterval` to handle the countdown timer, triggering a "Game Over" state the exact millisecond the clock hits zero.

---

## 🌟 Getting Started

Want to test your own brain or look at the code? It's incredibly easy to run locally:

1. **Clone the repository:**
```bash
git clone https://github.com/your-username/Donot_match_the_name_match_color.git

```


2. **Navigate to the project directory:**
```bash
cd Donot_match_the_name_match_color

```


3. **Open the game:**
Simply double-click `index.html` to open it in any modern web browser!

---

## 🔮 Future Improvements

* 🏆 **High Score Leaderboard:** Save your best scores using browser `localStorage`.
* 🔊 **Sound Effects:** Add satisfying click sounds for correct answers and a buzzer for mistakes.
* 📈 **Difficulty Modes:** Introduce a "Hard Mode" where the buttons shift positions after every click!

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the issues page if you want to contribute.

Made with ❤️ and a lot of brainpower! 🧠
