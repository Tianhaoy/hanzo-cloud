package com.hanzo.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 优先上传文件，文件会存放在缓存区，当用户提交信息后，
 * 调用moveFileToPath / moveFilesToPath 将转移至存储区
 * 最后需要定时清理缓存区
 * 优势在于异步提交，减少交互时间
 * @author by gezhitan
 * @date 2021/4/1.
 */
public class FileUtil{

    private final String PATH = getJarPath() + "CACHE" + File.separator;

    private final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 加载默认缓冲区
     * @throws Exception 异常
     */
    private Boolean checkCacheFilePath() throws Exception {
        return checkCacheFilePath(PATH);
    }

    /**
     * 检查自定义路径是否合法及存在，不存在会自动创建
     * @param path 缓存路径
     * @return Boolean 是否监测通过
     * @throws Exception 异常
     */
    private Boolean checkCacheFilePath(String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()){
            if ( !file.mkdirs() ){
                logger.error("创建默认缓存地址异常！请检查地址合法性！{}", path);
                return false;
            }
        }
        return true;
    }

    /**
     * 获取当前jar所在位置
     * @return
     */
    @SuppressWarnings("all")
    private String getJarPath()
    {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if(System.getProperty("os.name").contains("dows"))
        {
            path = path.substring(1);
        }
        if(path.contains("jar"))
        {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        String result = path.replace("target/classes/", "");
        try{
            return URLDecoder.decode(result, "UTF-8");
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 上传文件到缓存区( 使用默认缓存路径）
     * @param file 单个文件
     * @return 缓存文件的路径 如果不合法会返回null
     * @throws IOException 异常
     */
    public String uploadSingleToCache(MultipartFile file) throws IOException {
        try {
            if ( !checkCacheFilePath() ){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return saveFile(file, PATH);
    }

    /**
     * 上传文件到缓存区
     * @param file 单个文件流
     * @param path 指定缓存路径
     * @return 缓存文件的路径 如果不合法会返回null
     * @throws IOException 异常
     */
    public String uploadSingleToCache(MultipartFile file, String path) throws IOException {
        try {
            if ( !checkCacheFilePath(path) ){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return saveFile(file, path);
    }

    /**
     * 上传文件到缓存区( 使用默认缓存路径）
     * @param file 单个文件
     * @return 缓存文件的路径及名称 如果不合法会返回null
     * @throws IOException 异常
     */
    public CacheFile uploadSingleToCacheWithName(MultipartFile file) throws IOException {
        try {
            if ( !checkCacheFilePath() ){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String cachePath = saveFile(file, PATH);
        return cachePath == null ? null : new CacheFile(file.getOriginalFilename(), cachePath);
    }

    /**
     * 上传文件到缓存区
     * @param file 单个文件流
     * @param path 指定缓存路径
     * @return 缓存文件的路径及名称 如果不合法会返回null
     * @throws IOException 异常
     */
    public CacheFile uploadSingleToCacheWithName(MultipartFile file, String path) throws IOException {
        try {
            if ( !checkCacheFilePath(path) ){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String cachePath = saveFile(file, path);
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null){
            return null;
        }
        return cachePath == null ? null : new CacheFile(originalFilename.substring(0, originalFilename.lastIndexOf(".")), cachePath);
    }

    /**
     * 上传多个文件到缓存区( 使用默认缓存路径）
     * @param files 多个文件
     * @return 缓存文件的路径 如果不合法会返回null
     * @throws IOException 异常
     */
    public List<String> uploadMultiToCache(MultipartFile[] files) throws IOException {
        List<String> list = new LinkedList<>();
        for (MultipartFile file : files){
            String cachePath = uploadSingleToCache(file);
            if (cachePath == null){
                return null;
            }
            list.add(cachePath);
        }
        return list;
    }

    /**
     * 上传多个文件到缓存区
     * @param files 多个文件流
     * @param path 指定缓存路径
     * @return 缓存文件的路径 如果不合法会返回null
     * @throws IOException 异常
     */
    public List<String> uploadMultiToCache(MultipartFile[] files, String path) throws IOException {
        List<String> list = new LinkedList<>();
        for (MultipartFile file : files){
            String cachePath = uploadSingleToCache(file, path);
            if (cachePath == null){
                return null;
            }
            list.add(cachePath);
        }
        return list;
    }

    /**
     * 上传多个文件到缓存区( 使用默认缓存路径）
     * @param files 多个文件
     * @return 缓存文件的路径及名称 如果不合法会返回null
     * @throws IOException 异常
     */
    public List<CacheFile> uploadMultiToCacheWithName(MultipartFile[] files) throws IOException {
        List<CacheFile> list = new LinkedList<>();
        for (MultipartFile file : files){
            CacheFile cacheFile = uploadSingleToCacheWithName(file);
            if (cacheFile == null){
                return null;
            }
            list.add(cacheFile);
        }
        return list;
    }

    /**
     * 上传文件到缓存区
     * @param files 多个文件流
     * @param path 指定缓存路径
     * @return 缓存文件的路径及文件名称  如果不合法会返回null
     * @throws IOException 异常
     */
    public List<CacheFile> uploadMultiToCacheWithName(MultipartFile[] files, String path) throws IOException {
        List<CacheFile> list = new LinkedList<>();
        for (MultipartFile file : files){
            CacheFile cacheFile = uploadSingleToCacheWithName(file, path);
            if (cacheFile == null){
                return null;
            }
            list.add(cacheFile);
        }
        return list;
    }

    /**
     * 将缓存区的文件移动至指定位置（位置具体到文件名）
     * @param cachePath 缓存文件的地址
     * @param storePath 存放文件的地址
     * @return 是否成功
     */
    public Boolean moveFileToPath(String cachePath, String storePath) {
        File cacheFile = new File(cachePath);
        File storeFile = new File(storePath);
        return cacheFile.renameTo(storeFile);
    }

    /**
     * 将缓存区的文件批量转移
     * @param fileMoves FileMove对象中包含缓存文件地址和存储地址
     * @return 是否成功
     */
    public Boolean moveFilesToPath(List<FileMove> fileMoves) {
        for (FileMove cachePath : fileMoves){
            if ( !copy(cachePath.getCachePath(), cachePath.getStorePath()) ){
                return false;
            }
        }
        return true;
    }

    /**
     * 将目标文件转移到对应文件夹
     * @param srcPathStr 源文件地址
     * @param desPathStr 转移至 文件夹
     */
    private static Boolean copy(String srcPathStr, String desPathStr)
    {
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);
            byte[] data = new byte[1024 * 8];
            int len;
            while((len = fis.read(data))!=-1)
            {
                fos.write(data,0,len);
            }
            fis.close();//释放资源
            fos.close();//释放资源
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 缓存文件会按日期文件夹分类
     * @param file 文件
     * @param path 缓存地址
     * @return String缓存地址
     * @throws IOException 异常
     */
    private String saveFile(MultipartFile file, String path) throws IOException {
        String code = UUID.randomUUID().toString().replace("-", "");
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        if ( !file.isEmpty() && file.getOriginalFilename() != null){
            String cachePath = path + date + File.separator + code + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File cache = new File(cachePath);
            if ( !cache.getParentFile().exists() ){
                logger.warn("父路径不存在{}", cachePath);
                if ( !cache.getParentFile().mkdirs() ){
                    logger.error("创建文件父路径异常！{}", cachePath);
                    return null;
                }
            }
            file.transferTo(cache);
            return cachePath;
        }
        return null;
    }

    class CacheFile{

        /**
         * 文件名称
         */
        private String name;

        /**
         * 文件路径
         */
        private String path;

        CacheFile(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    class FileMove{
        /**
         * 缓存地址
         */
        private String cachePath;
        /**
         * 存储地址
         */
        private String storePath;

        public FileMove(String cachePath, String storePath) {
            this.cachePath = cachePath;
            this.storePath = storePath;
        }

        public String getCachePath() {
            return cachePath;
        }

        public void setCachePath(String cachePath) {
            this.cachePath = cachePath;
        }

        public String getStorePath() {
            return storePath;
        }

        public void setStorePath(String storePath) {
            this.storePath = storePath;
        }
    }

}
