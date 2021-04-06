﻿using IPhone.Application.Account;
using IPhone.Application.Exceptions;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace IPhone.Application.User.Profile
{
    public class UserProfileHandler : IRequestHandler<UserProfileCommand, UserViewModel>
    {

        public async Task<UserViewModel> Handle(UserProfileCommand request, CancellationToken cancellationToken)
        {
            return new UserViewModel
            {
                Image = "asfd.jpg"
            };
            //throw new RestException(HttpStatusCode.NotFound);
        }
    }
}
