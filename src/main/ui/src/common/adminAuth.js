class AdminAuth {
    constructor(){
        this.authenticated = false;
        this.email = 'admin@admin.com';
        this.password = 'admin';
    }

    login(email, password){

        if(email === this.email && password === this.password) {
            this.authenticated = true;
        } else {
            this.authenticated = false;
        }
    }

    logout(){
        this.authenticated = false;
    }

    isAuthenticated(){
        return this.authenticated;
    }

}
export default new AdminAuth();