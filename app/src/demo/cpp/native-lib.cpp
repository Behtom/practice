#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_practice_core_application_BaseApplication_flavor(JNIEnv* env, jobject) {
    std::string flavor = "DEMO";
    return env->NewStringUTF(flavor.c_str());
}