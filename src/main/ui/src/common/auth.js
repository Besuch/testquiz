class Auth {
    constructor(){
        this.authenticated = false;
        this.email = 'user@gmail.com';
        this.password = 'user';
        this.name = null;
        this.lastName = null;
    }

    login(email, password){

        alert(this.email + " " + this.password);
        if(email === this.email && password === this.password) {
            this.authenticated = true;
        } else {
            this.authenticated = false;
        }
       // cb()
    }

    logout(){
        this.authenticated = false;
    }

    isAuthenticated(){
        return this.authenticated;
    }

    signup(firstName, lastName, email, password){
        this.email = email;
        this.password = password;
        this.name = firstName;
        this.lastName = lastName;
    }
}
export default new Auth;