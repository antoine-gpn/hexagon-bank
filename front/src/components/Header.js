import "../styles/Header.css";
import logo from "../assets/exalt.avif";

function Header() {
    return (
        <header>
            <img src={logo} width={200} />
        </header>
    );
}

export default Header;
