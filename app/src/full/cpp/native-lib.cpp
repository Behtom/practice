#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICAL
Java_com_example_practice_BaseApplication_flavor(JNIEnv* env, jobject) {
    std::string flavor = "FULL";
    return env->NewStringUTF(flavor.c_str());
}