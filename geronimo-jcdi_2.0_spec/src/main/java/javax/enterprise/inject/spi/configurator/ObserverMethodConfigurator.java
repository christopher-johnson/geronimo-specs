/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package javax.enterprise.inject.spi.configurator;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.EventContext;
import javax.enterprise.inject.spi.ObserverMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

public interface ObserverMethodConfigurator<T> {

    /**
     * Read observer meta data from a existing {@link java.lang.reflect.Method}
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(Method method);

    /**
     * Read observer meta data from a existing {@link AnnotatedMethod}
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(AnnotatedMethod<?> method);

    /**
     * Read observer meta data from a existing ObserverMethod
     *
     * @param method to read meta data from
     * @return self
     */
    ObserverMethodConfigurator<T> read(ObserverMethod<T> method);

    /**
     * Set the class of the Bean containing this observer.
     * If not set, the extension class is used.
     *
     * @param type the bean class containing this configurator.
     * @return self
     */
    ObserverMethodConfigurator<T> beanClass(Class<?> type);

    /**
     * Set the type of the observed event
     *
     * @param type of the observed event
     * @return self
     */
    ObserverMethodConfigurator<T> observedType(Type type);

    /**
     * Add the qualifier to the observed event
     *
     * @param qualifier to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifier(Annotation qualifier);

    /**
     * Add all the qualifiers to the Observed event
     *
     * @param qualifiers to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifiers(Annotation... qualifiers);

    /**
     * Add all the qualifiers to the Observed event
     *
     * @param qualifiers to add to event
     * @return self
     */
    ObserverMethodConfigurator<T> addQualifiers(Set<Annotation> qualifiers);

    /**
     * Replace all qualifiers on the Observed event.
     *
     * @param qualifiers to put on event
     * @return self
     */
    ObserverMethodConfigurator<T> qualifiers(Annotation... qualifiers);

    /**
     * Replace all qualifiers on the Observed event.
     *
     * @param qualifiers to put on event
     * @return self
     */
    ObserverMethodConfigurator<T> qualifiers(Set<Annotation> qualifiers);

    /**
     * Set the {@link Reception} mode for the observer to build
     *
     * @param reception reception type
     * @return self
     */
    ObserverMethodConfigurator<T> reception(Reception reception);

    /**
     * Set the {@link TransactionPhase} for the observer to build
     *
     * @param transactionPhase phase for the observer
     * @return self
     */
    ObserverMethodConfigurator<T> transactionPhase(TransactionPhase transactionPhase);

    /**
     * Set the priority for the observer to build
     *
     * @param priority priority of the observer
     * @return self
     */
    ObserverMethodConfigurator<T> priority(int priority);

    /**
     * Define Consumer to call when event is notified
     *
     * @param callback to call for the event notification
     * @return self
     */
    ObserverMethodConfigurator<T> notifyWith(EventConsumer<T> callback);

    /**
     * Allows modification of the asynchronous status of the observer to build.
     *
     * @param async async status
     * @return self
     */
    ObserverMethodConfigurator<T> async(boolean async);

    /**
     *
     * @param <T> type of the event
     */
    @FunctionalInterface
    interface EventConsumer<T>
    {
        void accept(EventContext<T> eventContext) throws Exception;
    }
}

