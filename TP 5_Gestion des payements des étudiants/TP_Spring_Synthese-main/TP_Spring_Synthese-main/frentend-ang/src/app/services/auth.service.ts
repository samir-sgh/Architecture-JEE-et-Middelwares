import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public users:any={
    admin:{password:'1234',roles:['ADMIN']},
    user1:{password:'1234',roles:['STUDENTS']}
  }
  public username:any;
  public isAuthenticated:boolean=false;
  public roles:string[]=[];
  constructor(private router:Router) { }
  public login(username:string,password:string):boolean{
    if(this.users[username] && this.users[username]['password']==password)
    {this.username=username;
    this.isAuthenticated=true;
    this.roles = this.users[username]['roles'];
    console.log(this.roles);
    return true;}
    else
    return false;
  }
  logout() {
    this.isAuthenticated=false;
    this.roles=[];
    this.username=undefined;
    this.router.navigateByUrl('/login')
  }
}
