package com.sunbeam.dtos;

import java.util.LinkedHashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@SessionScope
@Component
public class PlayQueue extends LinkedHashSet<Integer> {

}
