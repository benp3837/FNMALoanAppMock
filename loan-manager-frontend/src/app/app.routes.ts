import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { UserHome } from './components/user-home/user-home';
import { ManagerHome } from './components/manager-home/manager-home';
import { Piglatin } from './components/piglatin/piglatin';
import { Profile } from './components/profile/profile';
import { Register } from './components/register/register';

export const routes: Routes = [
    {
        path:"",
        component:Login
    },
    {
        path:"userHome",
        component:UserHome
    },
    {
        path:"managerHome",
        component:ManagerHome
    },
    {
        path:"profile",
        component:Profile
    },
    {
        path:"register",
        component:Register
    },
    {
        path:"piglatin",
        component:Piglatin
    }
];
