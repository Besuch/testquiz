import React from 'react'
import {Route, Redirect} from 'react-router-dom';
import AdminAuth from './adminAuth';
export const ProtectedAdminRout = ({component: Component, ...rest}) => {
    return (
        <Route {...rest}
               render={props => {
                   if(AdminAuth.isAuthenticated()) {
                       return <Component {...props} />;
                   }else{
                       return <Redirect to ={
                           {
                               pathname: '/admin',
                               state: {
                                   from: props.location
                               }
                           }
                       }
                       />

                   }
               }}
        />
    );
};