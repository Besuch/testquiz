import React from 'react';
import auth from '../../common/auth';

const LandingPage = (props) => {
    console.log(props);
    return (<div>
        <h1>Landing page</h1>
    <button
    onClick={() => {
        auth.login();
        props.history.push('/quiz/' + 'user@gmail.com');
        }
    }>Login</button>
    </div>);

};

 export default LandingPage;