package com.example.common_api.communication

import com.example.common_api.event.Event
import com.example.common_api.navigation.NavigationCommand


interface NavigationCommunication : Communication<Event<NavigationCommand>> {
    class Base : Communication.Base<Event<NavigationCommand>>(), NavigationCommunication
}
