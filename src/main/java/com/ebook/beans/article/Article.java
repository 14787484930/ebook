package com.ebook.beans.article;

import lombok.Data;

/**
 * @author zxl
 * @date 2019/1/15 11:15
 * @describe
 */
@Data
public class Article {

    private String thumb_media_id; //图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
    private String author; //图文消息的作者
    private String title; //图文消息的标题
    private String content_source_url; //在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀
    private String content; //图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
    private String digest; //图文消息的描述，如本字段为空，则默认抓取正文前64个字
    private String show_cover_pic; //是否显示封面，1为显示，0为不显示
    private String need_open_comment; //Uint32 是否打开评论，0不打开，1打开
    private String only_fans_can_comment; //Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
}
