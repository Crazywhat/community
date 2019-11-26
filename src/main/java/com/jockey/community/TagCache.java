package com.jockey.community;

import com.jockey.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {

    public static List<TagDTO> get(){
        List<TagDTO> tagDTOs = new ArrayList<>();

        TagDTO language = new TagDTO();
        language.setTagCatalog("开发语言");
        language.setTags(Arrays.asList("javascript","php","css","html","html5","java","node.js","python","c++","c","golang","objective-c","typescript","shell","c#","swift","sass","bash","ruby","less","asp.net","lua","scala","coffeescript","rust","actionscript","erlang","perl"));
        tagDTOs.add(language);

        TagDTO platform = new TagDTO();
        platform.setTagCatalog("平台框架");
        platform.setTags(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails","tornado","koa","struts"));
        tagDTOs.add(platform);

        TagDTO server = new TagDTO();
        server.setTagCatalog("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
        tagDTOs.add(server);

        TagDTO database = new TagDTO();
        database.setTagCatalog("数据库和缓存");
        database.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql","memcache","dsqlserver","postgresql","sqlite"));
        tagDTOs.add(database);

        TagDTO tool = new TagDTO();
        tool.setTagCatalog("开发工具");
        tool.setTags(Arrays.asList("git","github","visual-studio-code","vim","sublime-text","xcode","intellij-idea","eclipse","maven","ide","svn","visual-studio","atom","emacs","textmate","hg"));
        tagDTOs.add(tool);

        TagDTO device = new TagDTO();
        device.setTagCatalog("平台框架");
        device.setTags(Arrays.asList("android","ios","chrome","windows","iphone","firefox","internet-explorer","safari","ipad","opera","apple-watch"));
        tagDTOs.add(device);

        TagDTO other = new TagDTO();
        other.setTagCatalog("其它");
        other.setTags(Arrays.asList("html5","react.js","搜索引擎","virtualenv","lucene"));
        tagDTOs.add(other);

        return tagDTOs;
    }

    public static String filterInvalidTags(String inputTags){
        String[] split = inputTags.split(",");

        List<TagDTO> tagDTOs = get();
        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t->!tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }

}
