package com.bicjo.resys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DomainTestGroup.class, ServerTestGroup.class })
public class AllTestGroup {

}
