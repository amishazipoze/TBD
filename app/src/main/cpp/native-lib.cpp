#include <jni.h>
#include <string>
#include <unistd.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_adsfb_testadxvideo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_adsfb_testadxvideo_Utils_Script_1Enc_getKeyfst(JNIEnv *env, jobject thiz) {
    std::string hello = "";
    pid_t pid = getpid();
    char path[64] = {0};
    sprintf(path, "/proc/%d/cmdline", pid);
    FILE *cmdline = fopen(path, "r");
    jobjectArray ret = nullptr;
    if (cmdline) {
        char application_id[64] = {0};
        fread(application_id, sizeof(application_id), 1, cmdline);
        const char *package = "com.threexvideoplayer.player.hdvideoplayer";

        if (strcmp(package, application_id) == 0) {
            hello = "dBmWkTcSGqgdee2v";
        } else {
            hello = "gfchghfhfhddfhfh";
        }
    }
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_adsfb_testadxvideo_Utils_Script_1Dec_getKeytrd(JNIEnv *env, jobject thiz) {

    std::string hello = "";
    pid_t pid = getpid();
    char path[64] = {0};
    sprintf(path, "/proc/%d/cmdline", pid);
    FILE *cmdline = fopen(path, "r");
    jobjectArray ret = nullptr;
    if (cmdline) {
        char application_id[64] = {0};
        fread(application_id, sizeof(application_id), 1, cmdline);
        const char *package = "com.threexvideoplayer.player.hdvideoplayer";

        if (strcmp(package, application_id) == 0) {
            hello = "dBmWkTcSGqgdee2v";
        } else {
            hello = "gfchghfhfhddfhfh";
        }
    }
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_adsfb_testadxvideo_Utils_AllStaticValue_VersionMatch(JNIEnv *env, jclass clazz, jstring android_id,
                                                        jstring referal_code) {
    const char *androidId1 = env->GetStringUTFChars(android_id, 0);
    const char *referralCode2 = env->GetStringUTFChars(referal_code, 0);

    std::string hello = "";

    pid_t pid = getpid();
    char path[64] = {0};
    sprintf(path, "/proc/%d/cmdline", pid);
    FILE *cmdline = fopen(path, "r");
    jobjectArray ret = nullptr;
    if (cmdline) {
        char application_id[64] = {0};
        fread(application_id, sizeof(application_id), 1, cmdline);
        const char *package = "com.threexvideoplayer.player.hdvideoplayer";

        if (strcmp(package, application_id) == 0) {
            hello = "{";
            hello.append("\"xVideoModel\":");
            hello.append("\"xVideoLogin\"");

            hello.append("");
            hello.append(",\"xVideoUserAndroidId\":");
            hello.append("\"");
            hello.append(androidId1);

            hello.append("\"");
            hello.append(",\"xVideoUserReferral\":");
            hello.append("\"");
            hello.append(referralCode2);


            hello.append("\"");
            hello.append(",\"xVideoPackageName\":");
            hello.append("\"");
            hello.append(package);
            hello.append("\"");

            hello.append("}");
        }
    }

    env->ReleaseStringUTFChars(android_id, androidId1);
    env->ReleaseStringUTFChars(referal_code, referralCode2);

    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_adsfb_testadxvideo_Utils_AllStaticValue_Uri(JNIEnv *env, jclass clazz) {
    std::string hello = "";
    pid_t pid = getpid();
    char path[64] = {0};
    sprintf(path, "/proc/%d/cmdline", pid);
    FILE *cmdline = fopen(path, "r");
    jobjectArray ret = nullptr;
    if (cmdline) {
        char application_id[64] = {0};
        fread(application_id, sizeof(application_id), 1, cmdline);
        const char *package = "com.threexvideoplayer.player.hdvideoplayer";

        if (strcmp(package, application_id) == 0) {
            hello = "http://142.93.222.159/x-video-player/api/v1/";
        } else {
            hello = "fghfbgb";
        }
    }
    return env->NewStringUTF(hello.c_str());
    // TODO: implement Uri()
}