
package com.example.servicetest;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class TestBinder extends Binder {

    public void test()
    {
        Log.v(LogTag.TAG, "test");
    }

    @Override
    public void attachInterface(IInterface owner, String descriptor) {
        // TODO Auto-generated method stub
        super.attachInterface(owner, descriptor);
    }

    @Override
    public String getInterfaceDescriptor() {
        // TODO Auto-generated method stub
        return super.getInterfaceDescriptor();
    }

    @Override
    public boolean pingBinder() {
        // TODO Auto-generated method stub
        return super.pingBinder();
    }

    @Override
    public boolean isBinderAlive() {
        // TODO Auto-generated method stub
        return super.isBinderAlive();
    }

    @Override
    public IInterface queryLocalInterface(String descriptor) {
        // TODO Auto-generated method stub
        return super.queryLocalInterface(descriptor);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags)
            throws RemoteException {
        Log.v(LogTag.TAG, "onTransact[data:" + data.toString() + "][flag:" + flags + "]");
        String request = data.readString();
        Log.v(LogTag.TAG, "onTransact[request:" + request+ "]");
        reply.writeString("my response");
        return true;
    }

    @Override
    public void dump(FileDescriptor fd, String[] args) {
        // TODO Auto-generated method stub
        super.dump(fd, args);
    }

    @SuppressLint("NewApi")
    @Override
    public void dumpAsync(FileDescriptor fd, String[] args) {
        // TODO Auto-generated method stub
        super.dumpAsync(fd, args);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter fout, String[] args) {
        // TODO Auto-generated method stub
        super.dump(fd, fout, args);
    }

    @Override
    public void linkToDeath(DeathRecipient recipient, int flags) {
        // TODO Auto-generated method stub
        super.linkToDeath(recipient, flags);
    }

    @Override
    public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
        // TODO Auto-generated method stub
        return super.unlinkToDeath(recipient, flags);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

}
