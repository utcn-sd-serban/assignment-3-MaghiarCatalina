import React from 'react';
import './App.css';

import {HashRouter, Switch, Route} from "react-router-dom";
import SmartUserLogin from './view/SmartUserLogin';
import SmartFrontPage from './view/SmartFrontPage';
import SmartViewAllQuestions from './view/SmartViewAllQuestions';
import SmartSearchTitle from './view/SmartSearchTitle';
import SmartSearchTag from './view/SmartSearchTag';

const App =()=> (
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact={true} component={SmartUserLogin} path="/" />
        <Route exact={true} component={SmartFrontPage} path="/home" />
        <Route exact={true} component={SmartViewAllQuestions} path="/view-all-questions" />
        <Route exact={true} component={SmartSearchTitle} path="/title-search/:title" />
        <Route exact={true} component={SmartSearchTag} path="/tag-search/:tag" />
      </Switch>
    </HashRouter>      
  </div>
)

export default App;
