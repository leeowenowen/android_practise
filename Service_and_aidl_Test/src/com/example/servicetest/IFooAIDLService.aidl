package com.example.servicetest;
import com.example.servicetest.Callback;
// Interface declaration
interface IFooAIDLService {
	//test method 
    String foo(in String arg, Callback callback);
}