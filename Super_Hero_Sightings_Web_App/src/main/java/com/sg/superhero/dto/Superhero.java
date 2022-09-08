/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 1:38 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Superhero
 * @Full_Class_Name: com.sg.superhero.dto.Superhero
 * @File_Name: Superhero.java
 */

package com.sg.superhero.dto;

import com.sg.superhero.dao.InputValidationException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Superhero
{

    //Database Validation Constants
    private final int NAME_MAX_LENGTH = 30;
    private final int DESCRIPTION_MAX_LENGTH = 450;
    private final int SUPERPOWER_MAX_LENGTH = 450;

    //Primary Key
    private int id;

    @NotBlank ( message = "Name field is required." )
    @Size ( max = NAME_MAX_LENGTH, message = "Name entry is too long. Max Length: "
        + NAME_MAX_LENGTH )
    private String name;

    @Size ( max = DESCRIPTION_MAX_LENGTH, message = "Description entry is too long. Max Length: "
        + DESCRIPTION_MAX_LENGTH )
    private String description;

    @Size ( max = SUPERPOWER_MAX_LENGTH, message = "Super Power entry is too long. Max Length: "
        + SUPERPOWER_MAX_LENGTH )
    private String superpower;

    //Optional Data members
    private List<Location> sightingLocations;
    private List<Organization> memberOrganizations;

    //TODO: Add Bridge Table for SuperPowers and SuperPowers Page.


    public Superhero()
    {
    }

    public Superhero( String name, String description, String superpower )
    {
        this.name = name;
        this.description = description;
        this.superpower = superpower;

        try
        {
            validateSuperheroMembers();
        } catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getSuperpower()
    {
        return superpower;
    }

    public void setSuperpower( String superpower )
    {
        this.superpower = superpower;
    }

    public List<Location> getSightingLocations()
    {
        return sightingLocations;
    }

    public void setSightingLocations( List<Location> sightingLocations )
    {
        this.sightingLocations = sightingLocations;
    }

    public List<Organization> getMemberOrganizations()
    {
        return memberOrganizations;
    }

    public void setMemberOrganizations( List<Organization> memberOrganizations )
    {
        this.memberOrganizations = memberOrganizations;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Superhero superhero = (Superhero) o;
        return id == superhero.id && name.equals( superhero.name ) && Objects.equals( description,
            superhero.description ) && Objects.equals( superpower, superhero.superpower );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, description, superpower );
    }

    //Validation function
    private void validateSuperheroMembers() throws Exception
    {
        List<String> errorMessages = new ArrayList<>();

        //Name
        if ( !hasContent( this.name ) )
        {
            errorMessages.add( "Name is required." );
        }
        if ( this.name.length() > NAME_MAX_LENGTH )
        {
            errorMessages.add( "Input Name is too long." );
        }

        //Description
        if ( this.description.length() > DESCRIPTION_MAX_LENGTH )
        {
            errorMessages.add( "Input Description is too long." );
        }

        if ( this.superpower.length() > SUPERPOWER_MAX_LENGTH )
        {
            errorMessages.add( "Input Superpower is too long." );
        }

        if ( !errorMessages.isEmpty() )
        {
            Exception validationEX = new Exception();
            for ( String error : errorMessages )
            {
                validationEX.addSuppressed( new InputValidationException( error ) );
            }
            throw validationEX;
        }
    }

    //Helper methods for validation function

    /**
     Method code adapted from: Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,
     the%20user%20about%20the%20issues
     @param field    object to be checked
     @param errorMsg error message to throw
     @param errors   error message array to append
     @return true is null
     */
    private boolean ensureNotNull( Object field, String errorMsg, List<String> errors )
    {
        boolean result = true;
        if ( field == null )
        {
            errors.add( errorMsg );
            result = false;
        }
        return result;
    }

    /**
     Method code adapted from: Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,
     the%20user%20about%20the%20issues
     @param stringToCheck String to validate
     @return true if not null or empty
     */
    private boolean hasContent( String stringToCheck )
    {
        return ( stringToCheck != null && stringToCheck.trim().length() > 0 );
    }
}//End of Superhero
