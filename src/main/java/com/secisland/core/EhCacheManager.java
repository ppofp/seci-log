/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheManager {
    private static CacheManager manager;

    static {
    	start();
    }
    public static void start() throws CacheException {
        if (manager != null) {
            return;
        }
        manager = new CacheManager("D:/secisland/p2p/code/p2pweb/src/main/resources/ehcache.xml");
    }

    public static void stop() {
        if (manager != null) {
            manager.shutdown();
            manager = null;
        }
    }

    public final static Element get(String name, Serializable key){
        //System.out.println("GET1 => " + name+":"+key);
        if(name!=null && key != null)
            return getCache(name).get(key);
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public final static <T> T get(Class<T> resultClass, String name, Serializable key){
        //System.out.println("GET2 => " + name+":"+key);
        if(name!=null && key != null)
            return (T)getCache(name).get(key);
        return null;
    }
    
    public final static void set(String name, Serializable key, Serializable value){
        if(name!=null && key != null && value!=null){
        	Element element = new Element( key, value );
        	getCache(name).put(element);      
        }
    }
     
    public final static void evict(String name, Serializable key){
        if(name!=null && key != null)
        	getCache(name).remove(key);      
    }
    
    public static Cache getCache(String name) throws CacheException {
    	Cache cache = manager.getCache(name);
        if( cache == null ){
            try {
            	manager.addCache(name);
                cache = manager.getCache(name);
            }
            catch (net.sf.ehcache.CacheException e) {
                throw new CacheException(e);
            }
        }
        return cache;
    }
}
