import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import createSagaMiddleware from 'redux-saga';
import RootReducers, { rootSaga } from './modules';
import reportWebVitals from './reportWebVitals';
import { check } from './modules/auth';
import App from './App';

const DEV_MODE = false;
const sagaMiddleware = createSagaMiddleware();

const DevTool = DEV_MODE ? composeWithDevTools(applyMiddleware(sagaMiddleware)) : applyMiddleware(sagaMiddleware)

const store = createStore(
  RootReducers,
  DevTool
);

function loadAuth() {
  try {
    store.dispatch(check());
  } catch (e) {
    console.error('Check function is not working');
  }
}

sagaMiddleware.run(rootSaga);
loadAuth();

ReactDOM.render((
  <Provider store={store}>
    <BrowserRouter>
        <App />
    </BrowserRouter>
  </Provider>
), document.getElementById('root'));

reportWebVitals();
