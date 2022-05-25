import { createStore, applyMiddleware } from 'redux'
import reduxThunk from 'redux-thunk'
import reducer from './reducers'
 
 console.log("==reducer====");
 console.log(reducer);

const store = createStore(reducer, applyMiddleware(reduxThunk));

export default store