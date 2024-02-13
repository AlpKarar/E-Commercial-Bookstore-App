import { useState } from 'react';
import Context from './Context';

const ContextProvider = (props) => {
    const [isAuth, setAuth] = useState(true);
  
    const states = {
        isAuth: isAuth,
        setAuth: setAuth
    };

    return (
      <Context.Provider value={states}>
        {props.children}
      </Context.Provider >
    )
  }

  export default ContextProvider;