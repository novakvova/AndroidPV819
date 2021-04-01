using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IPhone.Application.Account.Login
{
    public class LoginCommand : IRequest<UserViewModel>
    {
        public string Email { get; set; }
        public string Password { get; set; }
    }
}
