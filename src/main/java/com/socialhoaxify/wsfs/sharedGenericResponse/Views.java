package com.socialhoaxify.wsfs.sharedGenericResponse;

//Bu class loginPage de loginin başarılı olması durumunda dönmesini istediğimiz json body için oluşturduk.
//Daha öncede benzer yaklaşımla apiError package oluşturduk.
//Fakat password gibi bilgilerin görünmesini istemediğimiz için modelimize bu
// annotationu verip istenilen alan için bir response dönmesini istiyoruz.
// Bu optimum bir solution değildir.
public interface Views {
    class  Base{}
}
