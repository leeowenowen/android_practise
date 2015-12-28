LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := package_uninstall_watcher
LOCAL_SRC_FILES := com_example_test_JNIBridge.c
LOCAL_LDLIBS:=-L$(SYSROOT)/usr/lib -llog 
include $(BUILD_SHARED_LIBRARY)
