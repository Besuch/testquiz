import React from 'react';
import {NoMatch} from '../nomatch/NoMatch';
import {QuizList} from '../quizlist/QuizList';
import {Provider} from 'react-redux';
import {applyMiddleware, compose, createStore} from 'redux';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import createSagaMiddleware from "redux-saga";
import reducer from '../../reducer';
import {watchQuizSaga} from '../../saga'
import SignIn from '../signin/SignIn'
//import AdminResult from '../result/AdminResult'
import AdminResult from '../result/AdminResult'
import SignUp from '../signup/SignUp'
import AdminSignIn from '../adminsignin/AdminSignIn';
import {ProtectedRout} from "../../common/ProtectedRout";
import {ProtectedAdminRout} from "../../common/ProtectedAdminRout";


const sagaMiddleware = createSagaMiddleware();
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(reducer, composeEnhancers(applyMiddleware(sagaMiddleware)));
sagaMiddleware.run(watchQuizSaga);
window.store = store;

function App() {
  return (
      <Provider store={store}>
        <React.Fragment>
            <Router>
              <Switch>
                <Route exact path="/" component={SignIn}/>
                <Route exact path="/signup" component={SignUp}/>
                <ProtectedAdminRout exact path="/results" component={AdminResult}/>
                <Route exact path="/admin" component={AdminSignIn}/>
                <ProtectedRout exact path="/quiz/" component={QuizList}/>
                <Route component={NoMatch}/>
              </Switch>
            </Router>
        </React.Fragment>
      </Provider>
  );
}

export default App;
