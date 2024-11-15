package top.ybgnb.bilibili.backup.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import top.ybgnb.bilibili.backup.bean.ApiResult;
import top.ybgnb.bilibili.backup.bean.Video;
import top.ybgnb.bilibili.backup.error.BusinessException;
import top.ybgnb.bilibili.backup.request.ListApi;
import top.ybgnb.bilibili.backup.request.ModifyApi;
import top.ybgnb.bilibili.backup.user.User;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ToViewService
 * @Description 稍后再看
 * @Author hzhilong
 * @Time 2024/9/26
 * @Version 1.0
 */
@Slf4j
public class ToViewService extends BackupRestoreService {

    public ToViewService(OkHttpClient client, User user, String path) {
        super(client, user, path);
    }

    @Override
    public void backup() throws BusinessException {
        backupData("稍后再看", this::getList);
    }

    private List<Video> getList() throws BusinessException {
        return new ListApi<>(client, user, "https://api.bilibili.com/x/v2/history/toview/web",
                Video.class).getList();
    }

    @Override
    public void restore() throws BusinessException {
        log.info("正在还原[稍后再看]...");
        restoreList("稍后再看", Video.class, new RestoreCallback<Video>() {
            @Override
            public List<Video> getNewList() throws BusinessException {
                return getList();
            }

            @Override
            public String compareFlag(Video data) {
                return data.getBvid();
            }

            @Override
            public String dataName(Video data) {
                return String.format("视频[%s]", data.getTitle());
            }

            @Override
            public void restoreData(Video data) throws BusinessException {
                ApiResult<Void> apiResult = new ModifyApi<Void>(client, user,
                        "https://api.bilibili.com/x/v2/history/toview/add", Void.class).modify(
                        new HashMap<String, String>() {{
                            put("aid", String.valueOf(data.getAid()));
                        }}
                );
                if (apiResult._isFail()) {
                    throw new BusinessException(apiResult);
                }
            }
        });
    }
}
