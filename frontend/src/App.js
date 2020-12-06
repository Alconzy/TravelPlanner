import { Route } from 'react-router-dom';
import React from 'react';
import './App.css';

class App extends React.Component {
  render() {
    return (
        <>
          <Route exact path="/" component={() => (<div>react test</div>)} />
        </>
    );
  }
}

export default App;
