package com.newtouch.lion.redis;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author wanglijun
 * @version 1.0
 *          2015-12-07 14:44.
 */
public class AtomicReferenceTest {

    private  static AtomicReference<User>    userAtomicReference=new AtomicReference<User>();

    public static void main(String[] args) {

        User user = new User("conan", 15);

        userAtomicReference.set(user);

        User updateUser = new User("Shinichi", 17);

        userAtomicReference.compareAndSet(user, updateUser);

        System.out.println(userAtomicReference.get().getName());

        System.out.println(userAtomicReference.get().getOld());

    }

}
