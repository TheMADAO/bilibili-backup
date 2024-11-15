package top.ybgnb.bilibili.backup.service;

import okhttp3.OkHttpClient;
import top.ybgnb.bilibili.backup.user.User;

/**
 * @ClassName ServiceBuild
 * @Description
 * @Author hzhilong
 * @Time 2024/10/3
 * @Version 1.0
 */
public interface ServiceBuilder {
    BackupRestoreService build(OkHttpClient client, User user, String path);
}
