// App.js
import React from 'react';
import NavBar from './components/NavBar';
import SearchBar from './components/SearchBar';
import Recipes from './components/Recipes';
import SocialFeed from './components/SocialFeed';
import Footer from './components/Footer';

function App() {
  return (
      <div>
        <NavBar />
        <SearchBar />
        <Recipes />
        <SocialFeed />
        <Footer />
      </div>
  );
}

export default App;
